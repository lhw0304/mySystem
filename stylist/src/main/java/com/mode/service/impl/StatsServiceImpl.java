package com.mode.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mode.config.AppConfig;
import com.mode.config.Message;
import com.mode.config.Response;
import com.mode.dao.AccountDAO;
import com.mode.dao.ProfileDAO;
import com.mode.dao.StylistCommissionStatsDAO;
import com.mode.dao.ConfigDAO;
import com.mode.dao.HeadlineDAO;
import com.mode.entity.Account;
import com.mode.entity.StylistCommissionStats;
import com.mode.entity.Config;
import com.mode.entity.Headline;
import com.mode.entity.HeadlineStats;
import com.mode.entity.UserActionLog;
import com.mode.service.LogService;
import com.mode.service.StatsService;

/**
 * Created by zhaoweiwei on 15/11/19.
 */
@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private HeadlineDAO headlineDAO;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private ConfigDAO configDAO;

    @Autowired
    private StylistCommissionStatsDAO stylistCommissionStatsDAO;

    @Autowired
    private ProfileDAO profileDAO;

    @Autowired
    private LogService logService;

    @Value("${uri.logService}")
    private String logServiceUri;

    private static ObjectMapper om = new ObjectMapper();

    @Override
    public Response listStylistArticleStats(Integer stylistId, Integer limit, Integer offset) {

        Response res = new Response();
        List<HeadlineStats> headlineStatsesList = new ArrayList<HeadlineStats>();

        try {
            List<Headline> list = headlineDAO.listHeadlinesByAuthorId(stylistId, limit, offset);

            if (list == null || list.isEmpty()) {
                res.setMessage(Message.NO_MORE_STYLIST_CONTENT);
                return res;
            }

            Integer total = headlineDAO.getHeadlineCountByAuthorId(stylistId);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for (Headline headline : list) {
                HeadlineStats headlineStats = new HeadlineStats();
                Integer id = headline.getId();
                headlineStats.setId(id);
                headlineStats.setStatus(headline.getStatus());
                headlineStats.setType(headline.getType());
                headlineStats.setCtime(headline.getCtime());
                headlineStats.setUtime(headline.getUtime());
                headlineStats.setContent(headline.getContent());
                HeadlineStats.Stats stats = new HeadlineStats.Stats();
                headlineStats.getStatsList().add(stats);
                headlineStatsesList.add(headlineStats);
                params.add(new BasicNameValuePair("objectValueList", String.valueOf(id)));
            }

            String uri = logServiceUri + "/v2/stats/listStatsbyObjectValues?";
            JsonNode node = null;
            try {
                node = executeRequest(uri, params);
                node = node.get("payload").get("list");
            } catch (Exception e) {
                e.printStackTrace();
                node = null;
//                res.setMessage(Message.NO_MORE_RECORD);
//                return res;
            }
            if (node != null) {
                UserActionLog[] userActionLogs = om.readValue(node.traverse(),
                        UserActionLog[].class);
                for (HeadlineStats headlineStats : headlineStatsesList) {
                    Integer id = headlineStats.getId();
                    HeadlineStats.Stats stats = headlineStats.getStatsList().get(0);
                    for (UserActionLog userActionLog : userActionLogs) {
                        if (id.toString().equals(userActionLog.getObjectValue())) {
                            String action = userActionLog.getAction();
                            if ("view".equalsIgnoreCase(action)) {
                                stats.setView(userActionLog.getCount());
                                stats.setViewDistinct(userActionLog.getCount1());
                            } else if ("share".equalsIgnoreCase(action)) {
                                stats.setShare(userActionLog.getCount());
                                stats.setShareDistinct(userActionLog.getCount1());
                            } else if ("like".equalsIgnoreCase(action)) {
                                stats.setLike(userActionLog.getCount());
                                stats.setLikeDistinct(userActionLog.getCount1());
                            } else if ("save".equalsIgnoreCase(action)) {
                                stats.setSave(userActionLog.getCount());
                                stats.setSaveDistinct(userActionLog.getCount1());
                            } else if ("purchase".equalsIgnoreCase(action)) {
                                stats.setPurchase(userActionLog.getCount());
                                stats.setPurchaseDistinct(userActionLog.getCount1());
                            }
                        }
                    }
                    //headlineStats.getStatsList().add(stats);
                }
            }
            res.setMessage(Message.SUCCESS);
            Map<String, Object> payload = new HashMap<String, Object>();
            payload.put("list", headlineStatsesList);
            payload.put("total", total);
            res.setPayload(payload);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
            return res;
        }

        return res;
    }

    @Override
    public Response listStylistCommissionStats(Integer stylistId, Integer limit, Integer offset) {
        Response res = new Response();
        if (limit == null)
            limit = AppConfig.STYLIST_COMMISSION_STATS_DEFAULT_COUNT;

        try {
            List<StylistCommissionStats> list = stylistCommissionStatsDAO
                    .listStatsByStylistId(stylistId, limit, offset);
            if (list == null || list.size() == 0) {
                res.setMessage(Message.NO_MORE_RECORD);
                return res;
            }

            Map<String, Object> payload = new HashMap<>();
            payload.put("list", list);
            res.setPayload(payload);
            res.setMessage(Message.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }
        return res;
    }

    @Override
    public Response listStylistArticleStatsByDate(Integer acticleId, Integer limit) {
        Response res = new Response();
        HeadlineStats headlineStats = new HeadlineStats();
        if (limit == null)
            limit = 30;
        try {
            long now = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            long dailyMillis = 24 * 3600 * 1000;
            long startTime = sdf.parse(sdf.format(now)).getTime() - (limit - 1) * dailyMillis;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("objectValue", String.valueOf(acticleId)));
            params.add(new BasicNameValuePair("startTime", String.valueOf(startTime)));
            String uri = logServiceUri + "/v2/stats/listStatsbyObjectValueAndDate?";
            JsonNode node = executeRequest(uri, params);
            try {
                node = node.get("payload").get("list");
            } catch (Exception e) {
                e.printStackTrace();
                res.setMessage(Message.NO_MORE_RECORD);
                return res;
            }

            headlineStats.setId(acticleId);
            for (long i = startTime; i <= now; i = i + dailyMillis) {
                HeadlineStats.Stats stats = new HeadlineStats.Stats();
                stats.setDate(sdf.format(i));
                headlineStats.getStatsList().add(stats);
            }
            if (node != null) {
                UserActionLog[] userActionLogs = om.readValue(node.traverse(),
                        UserActionLog[].class);
                for (UserActionLog userActionLog : userActionLogs) {
                    String date = userActionLog.getDate();
                    String action = userActionLog.getAction();
                    Integer totalCount =  userActionLog.getCount();
                    Integer distinctCount = userActionLog.getCount1();
                    for (HeadlineStats.Stats stats : headlineStats.getStatsList()) {
                        if (date.equals(stats.getDate())) {
                            if ("view".equalsIgnoreCase(action)) {
                                stats.setView(totalCount);
                                stats.setViewDistinct(distinctCount);
                            } else if ("share".equalsIgnoreCase(action)) {
                                stats.setShare(totalCount);
                                stats.setShareDistinct(distinctCount);
                            } else if ("like".equalsIgnoreCase(action)) {
                                stats.setLike(totalCount);
                                stats.setLikeDistinct(distinctCount);
                            } else if ("save".equalsIgnoreCase(action)) {
                                stats.setSave(totalCount);
                                stats.setSaveDistinct(distinctCount);
                            } else if ("purchase".equalsIgnoreCase(action)) {
                                stats.setPurchase(totalCount);
                                stats.setPurchaseDistinct(distinctCount);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
            return res;
        }
        res.setMessage(Message.SUCCESS);
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("list", headlineStats);
        res.setPayload(payload);

        return res;
    }

    @Override
    public void stylistCommissionStats() {
        try {
            long now = System.currentTimeMillis();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            long dailyMillis = 24 * 3600 * 1000;
            List<Account> stylistAccounts = accountDAO.listStylists();
            for (Account stylistAccount : stylistAccounts) {
                Integer stylistId = stylistAccount.getUserId();
                Map<String, CalRule> hm = parseConfig(stylistId);
                try {
                    List<Headline> stylistHeadlines = headlineDAO.listHeadlinesByAuthorId(stylistId,
                            null, null);
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    for (Headline stylistHeadline : stylistHeadlines) {
                        params.add(new BasicNameValuePair("objectValueList",
                                String.valueOf(stylistHeadline.getId())));
                    }
                    Integer dateInt = stylistCommissionStatsDAO.getLatestStatsDateByStylistId
                            (stylistId);

                    Long startTime = null;
                    if (dateInt == null) {
                        Long ctime = headlineDAO.getEarliestCreateDateByAuthorId(stylistId);
                        if (ctime != null)
                            startTime = sdf.parse(sdf.format(ctime)).getTime();
                    } else {
                        startTime = sdf.parse(dateInt.toString()).getTime() + dailyMillis;
                    }
                    if (startTime != null) {
                        for (long l = startTime; l <= now - dailyMillis; l = l + dailyMillis) {
                            float usd = 0f;
                            String uri = logServiceUri + "/v2/stats/listStatsbyObjectValuesAndRange?";
                            params.add(new BasicNameValuePair("startTime", String.valueOf(l)));
                            params.add(new BasicNameValuePair("endTime",
                                    String.valueOf(l + dailyMillis)));
                            JsonNode node = null;
                            try {
                                node = executeRequest(uri, params);
                                params.remove(params.size() - 1);
                                params.remove(params.size() - 1);

                            } catch (Exception e) {
                                e.printStackTrace();
                                logService.createSystemLog("stylist", "stylistCommissionStats",
                                        stylistId, "remoteCall",
                                        "/v2/stats/listStatsbyObjectValuesAndRange", "failed", e
                                                .getMessage());
                                break;
                            }
                            StylistCommissionStats stylistCommissionStats = new
                                    StylistCommissionStats();
                            stylistCommissionStats.setStylistId(stylistId);
                            stylistCommissionStats.setDate(Integer.parseInt(sdf.format(l)));
                            stylistCommissionStats.setCtime(now);
                            try {
                                node = node.get("payload").get("list");
                            } catch (Exception e) {
                                // There is no record, usd is 0
                                stylistCommissionStats.setUsd(usd);
                                stylistCommissionStatsDAO.createStylistCommissionStats
                                        (stylistCommissionStats);
                                continue;
                            }
                            if (node != null) {
                                UserActionLog[] userActionLogs = om.readValue(node.traverse(),
                                        UserActionLog[].class);
                                for (UserActionLog userActionLog : userActionLogs) {
                                    String action = userActionLog.getAction();
                                    CalRule calRule = hm.get(action);
                                    if (calRule != null) {
                                        String type = calRule.getType();
                                        float value = calRule.getValue();
                                        if (AppConfig.CONFIG_STYLIST_COMMISSION_TYPE_TOTAL
                                                .equalsIgnoreCase(type))
                                            usd += value * userActionLog.getCount();
                                        else if (AppConfig.CONFIG_STYLIST_COMMISSION_TYPE_DISTINCT
                                                .equalsIgnoreCase(type))
                                            usd += value * userActionLog.getCount1();
                                    }
                                }
                                stylistCommissionStats.setUsd(usd);
                                stylistCommissionStatsDAO.createStylistCommissionStats
                                        (stylistCommissionStats);
                            }

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logService.createSystemLog("stylist", "stylistCommissionStats",
                            stylistId, null, null, "failed", e.getMessage());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            logService.createSystemLog("stylist", "stylistCommissionStats",
                    null, null, null, "fail", e.getMessage());
        }
    }

    private JsonNode executeRequest(String uri, List<NameValuePair> params) throws Exception {
        JsonNode node = null;
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            String paramString = URLEncodedUtils.format(params, "utf-8");
            uri += paramString;
            HttpGet get = new HttpGet(uri);
            try (CloseableHttpResponse response = httpClient.execute(get)) {
                try (BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()))) {
                    StringBuffer result = new StringBuffer();
                    String line = "";
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }
                    String httpResult = result.toString();
                    node = om.readTree(httpResult);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return node;
    }

    private Map<String, CalRule> parseConfig(Integer stylistId) throws Exception {
        Map<String, CalRule> hm = new HashMap<String, CalRule>();
        try {
            Integer level = profileDAO.getUserLevel(stylistId);
            if (level == null) {
                throw new Exception("Stylist level is null.");
            }
            String type = AppConfig.CONFIG_STYLIST_COMMISSION + ".level" + level;
            List<Config> list = configDAO.listConfigAdmin(null, null, type, null);
            for (Config config : list) {
                String attributeName = config.getAttributeName().trim();
                String attributeValue = config.getAttributeValue().trim();
                CalRule rule = new CalRule();
                float value = Float.valueOf(attributeValue);
                int index = attributeName.indexOf(".");
                if (index != -1) {
                    rule.setType(attributeName.substring(index + 1));
                    rule.setValue(value);
                }
                hm.put(attributeName.substring(0, index), rule);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return hm;
    }

    @Override
    public Response listConfig(Integer stylistId) {
        Response res = new Response();
        Integer level = profileDAO.getUserLevel(stylistId);
        if (level == null) {
            res.setMessage(Message.FAILURE);
            return res;
        }
        String type = AppConfig.CONFIG_STYLIST_COMMISSION + ".level" + level;
        List<Config> configs = configDAO.listConfigAdmin(null, null, type, null);
        if (configs.isEmpty() || configs == null) {
            res.setMessage(Message.FAILURE);
            return res;
        }
        Map<String, Object> payload = new HashMap<>();
        payload.put("list", configs);
        res.setMessage(Message.SUCCESS);
        res.setPayload(payload);
        return res;
    }

    private class CalRule {
        private String type;
        private float value;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
    }
}

package com.mode.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mode.dao.AccountDAO;
import com.mode.entity.Account;
import com.mode.service.AccountService;
import com.mode.service.StylistCommissionSettlementService;
import com.mode.service.LogService;
import com.mode.service.StatsService;
import com.mode.util.UserLockGenerator;

/**
 * Created by zhaoweiwei on 15/11/24.
 */
@Component
public class StylistCommissionStatsScheduler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StatsService statsService;

    @Autowired
    private StylistCommissionSettlementService stylistCommissionSettlementService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private LogService logService;

    @Scheduled(cron = "0 0 14 * * ?")
    public void stylistCommissionScheduler() {
        logger.info("Stylist Commission Stats $ Settlement service started");
        logService.createSystemLog("stylist", "stylistCommissionScheduler",
                null, null, null, "started", null);
        statsService.stylistCommissionStats();
        List<Account> stylistAccounts = accountService.listStylists();
        for (Account stylistAccount : stylistAccounts) {
            Integer stylistId = stylistAccount.getUserId();
            if (stylistId != null) {
                try {
                    Object lock = UserLockGenerator.getInstance().getLock(stylistId);
                    synchronized (lock) {
                        stylistCommissionSettlementService.settlement(stylistId);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logService.createSystemLog("stylist", "settlement",
                            stylistId, null, null, "failed", e.getMessage());
                }
            }
        }
        logService.createSystemLog("stylist", "StylistCommissionScheduler",
                null, null, null, "ended", null);
        logger.info("Stylist Commission Stats $ Settlement service ended");
    }

}

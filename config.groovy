environments {
    local {
        jdbc {
            url = 'jdbc:mysql://127.0.0.1:3306/my_system?useUnicode=true&characterEncoding=utf-8'
            username = 'root'
            password = 'root'
            maxActive = '5'
            initialSize = '1'
        }

        log4j {
            level = 'DEBUG'
        }

        thread {
            maxPoolSize = 10
            corePoolSize = 3
            queueCapacity = 100
            keepAliveSeconds = 300
        }

        uri {
            imageService = 'http://localhost:8080/platform-2.0-image'
            recommenderService = 'http://localhost:8080/platform-2.0-recommender'
            logService = 'http://localhost:8080/platform-2.0-log'
            webService = 'http://localhost:8080/web'
        }
    }

    test {
        jdbc {
            url = 'jdbc:mysql://10.168.179.86:3306/system?useUnicode=true&characterEncoding=utf-8'
            username = 'root'
            password = 'Kitetea2013'
            maxActive = '5'
            initialSize = '1'
        }

        log4j {
            level = 'DEBUG'
        }

        thread {
            maxPoolSize = 5
            corePoolSize = 2
            queueCapacity = 50
            keepAliveSeconds = 100
        }

        uri {
            imageService = 'http://10.168.139.157:8080/platform-2.0-image'
            recommenderService = 'http://10.168.139.157:8080/platform-2.0-recommender'
            logService = 'http://10.168.139.157:8080/'
            webService = 'http://test.whatsmode.com/web'
        }
    }

    prod {
        jdbc {
            url = 'jdbc:mysql://modeprod.mysql.rds.aliyuncs.com:3306/mode?useUnicode=true&characterEncoding=utf-8'
            username = 'mode'
            password = 'Kitetea2013'
            maxActive = '5'
            initialSize = '2'
        }

        log4j {
            level = 'INFO'
        }

        thread {
            maxPoolSize = 10
            corePoolSize = 3
            queueCapacity = 100
            keepAliveSeconds = 300
        }

        uri {
            imageService = 'http://10.172.96:8080/platform-2.0-image'
            recommenderService = 'http://10.172.127.96:8080/platform-2.0-recommender'
            logService = 'http://10.172.127.96:8080/'
            webService = 'http://www.whatsmode.com/web'
        }
    }

    slave {
        jdbc {
            url = 'jdbc:mysql://modeslave.mysql.rds.aliyuncs.com:3306/mode?useUnicode=true&characterEncoding=utf-8'
            username = 'mode'
            password = 'Kitetea2013'
            maxActive = '2'
            initialSize = '2'
        }

        log4j {
            level = 'INFO'
        }

        thread {
            maxPoolSize = 10
            corePoolSize = 3
            queueCapacity = 100
            keepAliveSeconds = 300
        }

        uri {
            imageService = 'http://10.172.96:8080/platform-2.0-image'
            recommenderService = 'http://10.172.127.96:8080/platform-2.0-recommender'
            logService = 'http://10.172.127.96:8080/'
            webService = 'http://www.whatsmode.com/web'
        }
    }
}

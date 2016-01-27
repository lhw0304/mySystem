environments {
    local {
        jdbc {
            url = 'jdbc:mysql://127.0.0.1:3306/mode?useUnicode=true&characterEncoding=utf-8'
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

}

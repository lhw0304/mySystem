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
    }

}

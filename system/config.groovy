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
            userService = 'http://localhost:8080/platform-2.0-user'
        }
    }

    test {
        jdbc {
            url = 'jdbc:mysql://10.51.17.242:3306/mode?useUnicode=true&characterEncoding=utf-8'
            username = 'mode'
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
            userService = 'http://test.whatsmode.com'
        }
    }

    prod {
        jdbc {
            url = 'jdbc:mysql://modeprod.mysql.rds.aliyuncs.com:3306/mode?useUnicode=true&characterEncoding=utf-8'
            username = 'mode'
            password = 'Kitetea2013'
            maxActive = '30'
            initialSize = '10'
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
            userService = 'http://api.whatsmode.com'
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
            userService = 'http://api.whatsmode.com'
        }
    }
}

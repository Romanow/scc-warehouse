package ru.romanow.warehouse.config

import org.springdoc.core.SpringDocUtils
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Pageable
import javax.annotation.PostConstruct

@Configuration
class WebConfiguration {

    @PostConstruct
    fun init() {
        SpringDocUtils.getConfig().replaceWithClass(
            Pageable::class.java,
            org.springdoc.core.converters.models.Pageable::class.java
        )
    }
}
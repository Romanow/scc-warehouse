package ru.romanow.warehouse.extensions

import org.springframework.cloud.contract.spec.internal.ClientDslProperty

class ConsumerUtils {
    companion object {
        fun anyLegoTechnic(): ClientDslProperty {
            return ClientDslProperty(PatternsUtils.legoTechnicName)
        }
    }
}
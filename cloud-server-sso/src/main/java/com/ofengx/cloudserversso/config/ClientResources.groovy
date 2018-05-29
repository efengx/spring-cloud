package com.ofengx.cloudserversso.config

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties
import org.springframework.boot.context.properties.NestedConfigurationProperty
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails

class ClientResources {

    @NestedConfigurationProperty
    private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails()

    @NestedConfigurationProperty
    private ResourceServerProperties resource = new ResourceServerProperties()

    AuthorizationCodeResourceDetails getClient() {
        return client
    }

    ResourceServerProperties getResource() {
        return resource
    }
}

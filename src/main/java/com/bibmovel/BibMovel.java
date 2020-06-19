package com.bibmovel;

import com.bibmovel.provider.AuthenticationFilter;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by vinibrenobr11 on 18/06/2020 at 18:04
 */
public class BibMovel extends ResourceConfig {

    public BibMovel() {
        packages("com.bibmovel");
        register(AuthenticationFilter.class);
    }
}

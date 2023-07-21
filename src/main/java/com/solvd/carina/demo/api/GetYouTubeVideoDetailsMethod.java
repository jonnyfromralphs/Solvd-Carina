package com.solvd.carina.demo.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.config.Configuration;

@Endpoint(url = "${base_url}/videos?part=snippet&part=contentDetails&part=statistics", methodType = HttpMethodType.GET)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetYouTubeVideoDetailsMethod extends AbstractApiMethodV2 {

    public GetYouTubeVideoDetailsMethod (String videoId) {
        replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
        addUrlParameter("id", videoId);
        addUrlParameter("key", System.getenv("API_KEY"));
    }
}
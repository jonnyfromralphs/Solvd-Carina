package com.solvd.carina.demo.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.*;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.config.Configuration;

@Endpoint(url = "${base_url}/search?part=snippet&maxResults=10", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/youtube/_get/search_rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetYouTubeSearchResultsMethod extends AbstractApiMethodV2 {

    public GetYouTubeSearchResultsMethod (String searchQuery) {
        replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
        addUrlParameter("q", searchQuery);
        addUrlParameter("key", System.getenv("API_KEY"));
    }
}

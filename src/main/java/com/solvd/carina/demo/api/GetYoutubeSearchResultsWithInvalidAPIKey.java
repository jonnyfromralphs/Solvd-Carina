package com.solvd.carina.demo.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.config.Configuration;

@Endpoint(url = "${base_url}/search", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/youtube/_get/search_rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetYoutubeSearchResultsWithInvalidAPIKey extends AbstractApiMethodV2 {
    public GetYoutubeSearchResultsWithInvalidAPIKey (String searchQuery) {
        replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
        addUrlParameter("part", "snippet");
        addUrlParameter("maxResults", "10");
        addUrlParameter("q", searchQuery);
        addUrlParameter("key", "Invalid API Key");
    }
}

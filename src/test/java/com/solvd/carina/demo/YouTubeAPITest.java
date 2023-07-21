package com.solvd.carina.demo;

import com.google.gson.JsonArray;
import com.solvd.carina.demo.api.*;
import com.zebrunner.carina.api.APIMethodPoller;
import com.zebrunner.carina.api.apitools.validation.JsonComparatorContext;
import com.zebrunner.carina.api.apitools.validation.JsonCompareKeywords;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.core.registrar.tag.Priority;
import com.zebrunner.carina.core.registrar.tag.TestPriority;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import kong.unirest.JsonNode;
import ognl.ObjectElementsAccessor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class YouTubeAPITest implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test()
    public void testGetYouTubeSearchResults() {
        GetYouTubeSearchResultsMethod getYouTubeSearchResults = new GetYouTubeSearchResultsMethod("funny cat videos");
        Response response = getYouTubeSearchResults.callAPIExpectSuccess();
        getYouTubeSearchResults.validateResponseAgainstSchema("api/youtube/_get/search_rs.schema");
        getYouTubeSearchResults.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
    }

    @Test()
    public void testGetYouTubeVideoByIdFromSearch() {
        GetYouTubeSearchResultsMethod getYouTubeSearchResults = new GetYouTubeSearchResultsMethod("Java beginners");
        Response searchResponse = getYouTubeSearchResults.callAPIExpectSuccess();
        String firstResultVideoId = searchResponse.getBody().jsonPath().get("items[0].id.videoId");

        GetYouTubeVideoDetailsMethod getYouTubeVideoDetails = new GetYouTubeVideoDetailsMethod(firstResultVideoId);
        Response videoResponse = getYouTubeVideoDetails.callAPIExpectSuccess();

        GetYouTubeCommentsForVideoMethod getYouTubeCommentsForVideo = new GetYouTubeCommentsForVideoMethod(firstResultVideoId);
        Response commentResponse = getYouTubeCommentsForVideo.callAPIExpectSuccess();

        String expectedChannelTitle = "Programming with Mosh";
        String actualChannelTitle = videoResponse.jsonPath().get("items[0].snippet.channelTitle");
        Assert.assertEquals(actualChannelTitle, expectedChannelTitle, "The channel names for the first search result differ.");

        String expectedLikeCount = "232575";
        String actualLikeCount = videoResponse.jsonPath().get("items[0].statistics.likeCount");
        Assert.assertEquals(actualLikeCount, expectedLikeCount, "The like count for the first search result differ.");

        getYouTubeCommentsForVideo.validateResponseAgainstSchema("api/youtube/_get/comments_rs.schema");
    }

    @Test
    public void testInvalidParamsForVideoMethod() {
        GetYouTubeVideoDetailsMethod getYouTubeVideoDetails = new GetYouTubeVideoDetailsMethod("Invalid video id");
        Response response = getYouTubeVideoDetails.callAPIExpectSuccess();

        int actualResults = response.jsonPath().get("pageInfo.totalResults");

        Assert.assertEquals(actualResults, 0, "An invalid video id should return 0 results.");
    }
}

package com.cq.summer.controller;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;


public class ESTest {
    public static void main(String[] args) throws  Exception{
        String[] ips = {"49.234.115.63:9200"};
        HttpHost[] httpHosts = new HttpHost[ips.length];
        for(int i=0;i<ips.length;i++){
            httpHosts[i] = HttpHost.create(ips[i]);
        }
        String baseIndex = "conference";
        String baseType = "event";
        String id = "1";
        RestClientBuilder builder = RestClient.builder(httpHosts);
        RestHighLevelClient client = new RestHighLevelClient(builder);
        GetRequest request = new GetRequest(baseIndex, baseType, id + "");
        RequestOptions options = RequestOptions.DEFAULT;
        GetResponse response = client.get(request,options);
        System.err.println(response);
    }





//    public CorpSearchCorpcontactBaseinfoResponse.PageResult search(QueryObject qo) throws Exception {
//        SearchRequest request = new SearchRequest();
//        request.indices(baseIndex);
//        request.types(baseType);
//        SearchSourceBuilder sourceBuilder = qo.createSearchSourceBuilder();
//        HighlightBuilder highlightBuilder = qo.createHighlightBuilder();
//        sourceBuilder.highlighter(highlightBuilder);
//        request.source(sourceBuilder);
//        SearchResponse response = client.search(request);
//        SearchHits searchHits = response.getHits();
//        long total = searchHits.getTotalHits();
//        SearchHit[] searchHitArray = searchHits.getHits();
//        List<T> data = new ArrayList<>();
//        for(SearchHit hit : searchHitArray){
//            Map<String, Object> source = hit.getSourceAsMap();
//            T t = BeanUtil.map2Bean(source, clazz);
//            qo.setHighlightFields(t,hit);
//            data.add(t);
//        }
//        return new CorpSearchCorpcontactBaseinfoResponse.PageResult(data,Integer.parseInt(total+""),qo.getCurrentPage(),qo.getPageSize());
//
//    }
}

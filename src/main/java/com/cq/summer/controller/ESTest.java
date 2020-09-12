package com.cq.summer.controller;

import com.alibaba.druid.support.json.JSONUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ESTest {
    public static void main(String[] args) throws  Exception{
//        String[] ips = {"49.234.115.63:9200"};
        String[] ips = {"112.65.124.30:9200"};
        HttpHost[] httpHosts = new HttpHost[ips.length];
        for(int i=0;i<ips.length;i++){
            httpHosts[i] = HttpHost.create(ips[i]);
        }

        String id = "1";
        RestClientBuilder builder = RestClient.builder(httpHosts);
        RestHighLevelClient client = new RestHighLevelClient(builder);
        RequestOptions options = RequestOptions.DEFAULT;
//        get(client,options);

//        index(client,options);
//        testCreateIndex(client);
//        testExistIndex(client);
        queryCustChat(client);
    }


    private static void queryCustChat(RestHighLevelClient client) throws Exception{
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
        queryBuilder.must(
                QueryBuilders.rangeQuery("chatDate")
                        .gte("2020-08-01")
                        .lte("2020-08-05")
                        .relation("within")
        );
        queryBuilder.must(
                QueryBuilders.matchPhraseQuery("customerName","邻家的大女孩儿")
        );
        queryBuilder.must(
                QueryBuilders.matchPhraseQuery("customerName","邻家的大女孩儿")
        );
        queryBuilder.must(
                QueryBuilders.matchPhraseQuery("customerName","邻家的大女孩儿")
        );
        queryBuilder.must(
                QueryBuilders.matchPhraseQuery("customerName","邻家的大女孩儿")
        );


        searchSourceBuilder.query(queryBuilder);
        SearchRequest rq = new SearchRequest();
        rq.indices("100217");
        rq.source(searchSourceBuilder);
        SearchResponse rp = client.search(rq,RequestOptions.DEFAULT);
        List<Map<String, Object>> aa = Arrays.stream(rp.getHits().getHits()).map(b -> {
            System.out.println(b.getSourceAsMap());
            return b.getSourceAsMap();
        }).collect(Collectors.toList());
        System.out.println(JSONUtils.toJSONString(aa));



    }


    private static void testCreateIndex(RestHighLevelClient client) throws IOException {
        // 1、创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("kuang_index");
        // 2、客户端执行请求 IndicesClient,请求后获得响应
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    private static void testExistIndex(RestHighLevelClient client) throws IOException {
        GetIndexRequest request = new GetIndexRequest("kuang_index");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }


    private static void get(RestHighLevelClient client,RequestOptions options)throws  Exception{
        String baseIndex = "chat";
        String baseType = "_doc";
        GetRequest request = new GetRequest(baseIndex,"YWmFIHQByyavCxkdW0Jt");
        GetResponse response = client.get(request,options);
        System.err.println(response);
    }

    private static void index(RestHighLevelClient client,RequestOptions options)throws  Exception{
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("shop_name","sandro官方旗舰店");
        jsonMap.put("customer_name","carl");
        jsonMap.put("service_name","caicai19881023");
        jsonMap.put("send_type","1");
        jsonMap.put("send_time","2020-08-07 15:28:20");
        jsonMap.put("send_detail","http://item.taobao.com/item.htm?id=619036903505");
        IndexRequest indexRequest = new IndexRequest("chat","sandro").source(jsonMap, XContentType.JSON);
        try{
            IndexResponse indexResponse = client.index(indexRequest,options);
            System.out.println(indexResponse);
        }catch(Exception e){

        }


        //IndexResponse[index=chat,type=_doc,id=YWmFIHQByyavCxkdW0Jt,version=1,result=created,seqNo=0,primaryTerm=1,shards={"total":2,"successful":1,"failed":0}]
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

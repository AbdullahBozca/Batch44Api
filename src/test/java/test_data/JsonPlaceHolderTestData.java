package test_data;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public Map<String, Object> setUpTestData(){

        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("completed", false);
        expectedData.put("userId", 1);
        expectedData.put("via", "1.1 vegur");
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("Server", "cloudflare");
        return expectedData;
    }

    /*
        {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false
  }
     */

    public JSONObject setUpPostData(){
        JSONObject expectedRequest = new JSONObject();
        expectedRequest.put("userId", 55);
        expectedRequest.put("title", "Tidy your room");
        expectedRequest.put("completed", false);
        expectedRequest.put("statusCode", 201);
        expectedRequest.put("id", 201);
        return expectedRequest;
    }

    /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde
    {
    "userId": 21,
    "title": "Wash the dishes",
    "completed": false
    }
    */

    //PutRequest01
    public JSONObject setUpPutData(){
        JSONObject expectedRequest = new JSONObject();
        expectedRequest.put("userId", 21);
        expectedRequest.put("title", "Wash the dishes");
        expectedRequest.put("completed", false);
        return expectedRequest;
    }

       /*
   https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
  {
     "title": "Batch44"
    }
    */
    //PatchRequest01
    public JSONObject setUpPatchRequestData(){
        JSONObject requestData = new JSONObject();
        requestData.put("title", "Batch44");
        return requestData;
    }

    /*
     {
 "userId": 10,
 "title": "Batch44"
 "completed": true,
 "id": 198
 }
     */

    //PatchRequest01
    public JSONObject setUpPatchExpectedData(){
        JSONObject expectedData = new JSONObject();
        expectedData.put("userId", 10);
        expectedData.put("title", "Batch44");
        expectedData.put("completed", true);
        expectedData.put("id", 198);
        return expectedData;
    }
}
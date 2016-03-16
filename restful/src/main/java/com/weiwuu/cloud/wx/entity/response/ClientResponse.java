package com.weiwuu.cloud.wx.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * Created by hui on 2015/11/5.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class ClientResponse<T>
{
    private ClientResponse<T>.ResponseHead head;
    private ResponseBody body;

    public ClientResponse<T>.ResponseHead getHead()
    {
        return this.head;
    }

    public ResponseBody getBody() {
        return this.body;
    }
    public ClientResponse build(int resultCode, String message, int count, T data) {
        ClientResponse rc = new ClientResponse();
        rc.head = new ResponseHead(resultCode, message, count);
        rc.body = new ResponseBody(data);
        return rc;
    }

    public class ResponseBody<T>
    {
        private T data;

        public ResponseBody()
        {
        }

        @JsonProperty
        public T getData()
        {
            return this.data;
        }
        public ResponseBody(T data) {
            this.data = data;
        }
    }

    public class ResponseHead
    {
        private int code;
        private String message;
        private int itemCount;

        public ResponseHead()
        {
        }

        @JsonProperty
        public int getCode()
        {
            return this.code;
        }
        @JsonProperty
        public void setCode(int code) {
            this.code = code;
        }

        @JsonProperty
        public String getMessage() {
            return this.message;
        }

        @JsonProperty
        public int getItemCount() {
            return this.itemCount;
        }
        public ResponseHead(int code, String message, int itemCount) {
            this.itemCount = itemCount;
            this.code = code;
            this.message = message;
        }
    }

}

package com.weiwuu.cloud.wx.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseContent<T>
{
    public class ResponseHead
    {
        private int Code;

        @JsonProperty
        public int getCode()
        {
            return this.Code;
        }

        private String Message;

        @JsonProperty
        public String getMessage()
        {
            return this.Message;
        }

        private int ItemCount;

        @JsonProperty
        public int getItemCount()
        {
            return ItemCount;
        }

        public ResponseHead(int code, String message, int itemCount)
        {
            this.ItemCount = itemCount;
            this.Code = code;
            this.Message = message;
        }
    }

    public class ResponseBody<T>
    {
        private T Data;

        @JsonProperty
        public T getData()
        {
            return this.Data;
        }

        public ResponseBody(T data)
        {
            this.Data = data;
        }
    }

    private ResponseHead Head;

    public ResponseHead getHead()
    {
        return this.Head;
    }

    private ResponseBody Body;

    public ResponseBody getBody()
    {
        return this.Body;
    }

    public ResponseContent build(int resultCode, String message, int count, T data)
    {
        ResponseContent rc = new ResponseContent();
        rc.Head = new ResponseHead(resultCode, message, count);
        rc.Body = new ResponseBody(data);
        return rc;
    }
}

package net.gongmingqm10.zhihu.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Link implements Serializable {
    private String web;
    private String twitter;
}

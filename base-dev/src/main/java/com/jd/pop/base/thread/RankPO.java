package com.jd.pop.base.thread;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 榜单 PO
 * <p>
 *
 * @author sunboyu
 * @date 2017/11/13
 */
public class RankPO {

    private static final long serialVersionUID = 8847738631427613068L;
    /**
     * 审核人
     */
    private String auditor;
    /**
     * 审核时间
     */
    private LocalDateTime auditedTime;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;
    /**
     * 榜单来源
     */
    private SourceTypePO source;
    /**
     * 热点词
     */
    private String chinadaily;
    /**
     * 标签
     */
    private List<String> tagList;
    /**
     * 关键词
     */
    private List<String> keywordList;
    /**
     * 分类
     */
    private List<CategoryTypePO> categoryList;
    /**
     * 热度指数
     */
    private Double heatScore;
    /**
     * 排名
     */
    private Integer ranking;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public LocalDateTime getAuditedTime() {
        return auditedTime;
    }

    public void setAuditedTime(LocalDateTime auditedTime) {
        this.auditedTime = auditedTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public SourceTypePO getSource() {
        return source;
    }

    public void setSource(SourceTypePO source) {
        this.source = source;
    }

    public String getChinadaily() {
        return chinadaily;
    }

    public void setChinadaily(String chinadaily) {
        this.chinadaily = chinadaily;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public List<String> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(List<String> keywordList) {
        this.keywordList = keywordList;
    }

    public List<CategoryTypePO> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryTypePO> categoryList) {
        this.categoryList = categoryList;
    }

    public Double getHeatScore() {
        return heatScore;
    }

    public void setHeatScore(Double heatScore) {
        this.heatScore = heatScore;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
}
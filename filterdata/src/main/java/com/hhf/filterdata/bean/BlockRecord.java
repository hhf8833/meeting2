package com.hhf.filterdata.bean;

/**
 * @author HP
 * {"cid":{"/":"QmeeS4cQmULKW4xmR1yRQKPTvsQNFhkYXVjWWrFQ6EKSYs"},
 *  "event":"BlockService.BlockAdded","system":"blockservice","time":"2021-11-27T04:18:19.934773641Z"}
 */
public class BlockRecord {
    private Cid  cid;
    private String event;
    private String requestId;
    private String system;
    private String time;

    public Cid getCid() {
        return cid;
    }

    public void setCid(Cid cid) {
        this.cid = cid;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "BlockRecord{" +
                "cid=" + cid +
                ", event='" + event + '\'' +
                ", requestId='" + requestId + '\'' +
                ", system='" + system + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}

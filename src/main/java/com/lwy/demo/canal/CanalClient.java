package com.lwy.demo.canal;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import java.net.InetSocketAddress;
import java.util.List;

public class CanalClient {
    public static void main(String[] args) throws
            InvalidProtocolBufferException {
        //1.获取 canal 连接对象
        CanalConnector canalConnector =
                CanalConnectors.newSingleConnector(new
                        InetSocketAddress("192.168.10.177", 11111), "example", "", "");
        while (true) {
            //2.获取连接
            canalConnector.connect();
            //3.指定要监控的数据库
            canalConnector.subscribe(".*\\..*");
            //4.获取 Message
            Message message = canalConnector.get(100);
            List<CanalEntry.Entry> entries = message.getEntries();
            if (entries.size() <= 0) {
                System.out.println("没有数据，休息一会");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                for (CanalEntry.Entry entry : entries) {
                    //TODO 获取表名
                    String tableName =
                            entry.getHeader().getTableName();
                    // TODO Entry 类型
                    CanalEntry.EntryType entryType =
                            entry.getEntryType();
                    // TODO 判断 entryType 是否为 ROWDATA
                    if
                    (CanalEntry.EntryType.ROWDATA.equals(entryType)) {
                        // TODO 序列化数据
                        ByteString storeValue = entry.getStoreValue();
                        // TODO 反序列化
                        CanalEntry.RowChange rowChange =
                                CanalEntry.RowChange.parseFrom(storeValue);
                        //TODO 获取事件类型
                        CanalEntry.EventType eventType =
                                rowChange.getEventType();
                        //TODO 获取具体的数据
                        List<CanalEntry.RowData> rowDatasList =
                                rowChange.getRowDatasList();
                        //TODO 遍历并打印数据
                        for (CanalEntry.RowData rowData : rowDatasList)
                        {
                            List<CanalEntry.Column> beforeColumnsList =
                                    rowData.getBeforeColumnsList();
                            JSONObject beforeData = new JSONObject();
                            for (CanalEntry.Column column :
                                    beforeColumnsList) {
                                beforeData.put(column.getName(),
                                        column.getValue());
                            }
                            JSONObject afterData = new JSONObject();
                            List<CanalEntry.Column> afterColumnsList =
                                    rowData.getAfterColumnsList();
                            for (CanalEntry.Column column :
                                    afterColumnsList) {
                                afterData.put(column.getName(),
                                        column.getValue());
                            }
                            System.out.println("TableName:" + tableName
                                    +
                                    ",EventType:" + eventType +
                                    ",Before:" + beforeData +
                                    ",After:" + afterData);
                        }
                    }
                }
            }
        }
    } }
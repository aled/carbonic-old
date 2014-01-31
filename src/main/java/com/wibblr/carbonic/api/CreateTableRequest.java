package com.wibblr.carbonic.api;

import java.util.Collection;

public class CreateTableRequest extends Request {

    public TableProperties tableProperties;

    public static class TableProperties {
        public String name;
        public Collection<Column> columns;
        public Collection<String> primaryKey;
    }

    public static class Column {
        public String name;
        public String type;
        public Integer maxLength;
        public Boolean nullable;
    }

    //public static class Columns {
    //    public Collection<Column> columns;
    //}

    //public static class PrimaryKeyColumns {
     //   public Collection<String> primaryKeyColumns;
    //}
}

package com.nontage;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class SQLResult implements Iterable<SQLRow> {
    private final List<SQLRow> rows = new ArrayList<>();

    public SQLResult(ResultSet result) {
        try {
            while (result.next())
                rows.add(new SQLRow(result));
            result.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return rows.size();
    }

    public SQLRow get(int i) {
        if (rows.size() <= i)
            return null;
        return rows.get(i);
    }

    @Override
    public Iterator<SQLRow> iterator() {
        return rows.iterator();
    }

    @Override
    public void forEach(Consumer<? super SQLRow> action) {
        rows.forEach(action);
    }

    public SQLField first() {
        if (rows.isEmpty()) {
            return null;
        }
        if (rows.get(0).isEmpty()) {
            return null;
        }
        return rows.get(0).get(0);
    }

    public BigDecimal getBigDecimal() {
        return first().getBigDecimal();
    }

    public Blob getBlob() {
        return first().getBlob();
    }

    public Boolean getBoolean() {
        return first().getBoolean();
    }

    public byte[] getBytes() {
        return first().getBytes();
    }

    public Double getDouble() {
        return first().getDouble();
    }

    public Float getFloat() {
        return first().getFloat();
    }

    public Integer getInt() {
        return first().getInt();
    }

    public Long getLong() {
        return first().getLong();
    }

    public String getString() {
        return first().getString();
    }
}
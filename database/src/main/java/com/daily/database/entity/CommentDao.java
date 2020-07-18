package com.daily.database.entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COMMENT".
*/
public class CommentDao extends AbstractDao<Comment, String> {

    public static final String TABLENAME = "COMMENT";

    /**
     * Properties of entity Comment.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property CommentGUID = new Property(0, String.class, "commentGUID", true, "COMMENT_GUID");
        public final static Property UserName = new Property(1, String.class, "userName", false, "USER_NAME");
        public final static Property Body = new Property(2, String.class, "body", false, "BODY");
        public final static Property ArticleGUID = new Property(3, String.class, "articleGUID", false, "ARTICLE_GUID");
        public final static Property Timestamp = new Property(4, Long.class, "timestamp", false, "TIMESTAMP");
    }


    public CommentDao(DaoConfig config) {
        super(config);
    }
    
    public CommentDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COMMENT\" (" + //
                "\"COMMENT_GUID\" TEXT PRIMARY KEY NOT NULL ," + // 0: commentGUID
                "\"USER_NAME\" TEXT," + // 1: userName
                "\"BODY\" TEXT," + // 2: body
                "\"ARTICLE_GUID\" TEXT," + // 3: articleGUID
                "\"TIMESTAMP\" INTEGER);"); // 4: timestamp
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COMMENT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Comment entity) {
        stmt.clearBindings();
 
        String commentGUID = entity.getCommentGUID();
        if (commentGUID != null) {
            stmt.bindString(1, commentGUID);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(2, userName);
        }
 
        String body = entity.getBody();
        if (body != null) {
            stmt.bindString(3, body);
        }
 
        String articleGUID = entity.getArticleGUID();
        if (articleGUID != null) {
            stmt.bindString(4, articleGUID);
        }
 
        Long timestamp = entity.getTimestamp();
        if (timestamp != null) {
            stmt.bindLong(5, timestamp);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Comment entity) {
        stmt.clearBindings();
 
        String commentGUID = entity.getCommentGUID();
        if (commentGUID != null) {
            stmt.bindString(1, commentGUID);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(2, userName);
        }
 
        String body = entity.getBody();
        if (body != null) {
            stmt.bindString(3, body);
        }
 
        String articleGUID = entity.getArticleGUID();
        if (articleGUID != null) {
            stmt.bindString(4, articleGUID);
        }
 
        Long timestamp = entity.getTimestamp();
        if (timestamp != null) {
            stmt.bindLong(5, timestamp);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public Comment readEntity(Cursor cursor, int offset) {
        Comment entity = new Comment( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // commentGUID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // body
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // articleGUID
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4) // timestamp
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Comment entity, int offset) {
        entity.setCommentGUID(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setUserName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setBody(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setArticleGUID(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTimestamp(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
     }
    
    @Override
    protected final String updateKeyAfterInsert(Comment entity, long rowId) {
        return entity.getCommentGUID();
    }
    
    @Override
    public String getKey(Comment entity) {
        if(entity != null) {
            return entity.getCommentGUID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Comment entity) {
        return entity.getCommentGUID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

package com.polytech.si3.mypolytech.db;

import android.content.Context;
import android.database.Cursor;

import com.polytech.si3.mypolytech.article.Event;

/**
 * Created by david on 21/04/2016.
 */
public class EventsDBHelper extends AbstractDBHelper<Event> {

    public EventsDBHelper(Context context){
        super(context,"events_database");
        changeSelectCommand("SELECT * FROM Events ORDER BY date");
    }

    @Override
    public Event getElement(int id, Cursor cursor){
        Event event = new Event(id);

        event.setTitle(cursor.getString(1));
        event.setBody(cursor.getString(2));
        event.setAuthor(cursor.getString(3));
        event.setDate(cursor.getString(4));
        event.setCategory(cursor.getInt(5));
        event.setMedia(cursor.getInt(6),cursor.getString(7));
        event.setLocation(id,cursor.getString(8),cursor.getFloat(9),cursor.getFloat(10));

        return event;
    }
}

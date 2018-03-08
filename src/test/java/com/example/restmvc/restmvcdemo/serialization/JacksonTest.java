package com.example.restmvc.restmvcdemo.serialization;

import com.example.restmvc.restmvcdemo.errorhandling.RestError;
import com.example.restmvc.restmvcdemo.errorhandling.RestErrorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

public class JacksonTest {

    @Test
    public void whenSerializingDateWithJackson_thenSerializedToTimestamp()
            throws ParseException, JsonProcessingException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = sdf.parse("14-03-2018 01:02");
        Event event = new Event("party", date);

        ObjectMapper om = new ObjectMapper();
        //om.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        //om.setDateFormat(sdf);
        System.out.println(om.writeValueAsString(event));
    }

    @Test
    public void whenDeserialJsonStringThenConvertToDate() throws IOException {
        String json = "{\"event\":\"party\", \"date\":\"14-03-2018 01:02:00.000 AM UTC\"}";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(df);

        Event event = mapper.readerFor(Event.class).readValue(json);
        assertEquals("14-03-2018 01:02:00", df.format(event.getDate()));

    }

    @Test
    public void whenSerializeRestErrorMessageThenJsonCreated() throws JsonProcessingException {
        RestError restError = new RestError("", "", "");
        List<RestError> list = new ArrayList<>();
        list.add(restError);
        RestErrorMessage restErrorMessage = new RestErrorMessage.RestErrorMessageBuilder("1", "url").
                errorList(list).build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(restErrorMessage);

        System.out.println(json);
    }
}

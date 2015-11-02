package com.ghinwa.ericsson.entry;

import java.util.Date;
import java.util.UUID;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Entry {
	
	private String id = UUID.randomUUID().toString();

	@NotBlank
	private String title;

	private final Date publishedOn = new Date();

	public Entry() {
    }

	public Entry(String title) {
        super();
        this.title = title;
    }

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Date getPublishedOn() {
		return publishedOn;
	}
}
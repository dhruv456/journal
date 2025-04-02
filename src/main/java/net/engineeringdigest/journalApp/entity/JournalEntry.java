package net.engineeringdigest.journalApp.entity;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journalEntries")
@Getter
@Setter
public class JournalEntry {
    @Id
    private ObjectId id;
    @NotNull
    private String title;
    private String content;
    private LocalDateTime date;
}

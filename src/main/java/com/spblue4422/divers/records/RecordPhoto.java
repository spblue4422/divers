package com.spblue4422.divers.records;

import com.spblue4422.divers.common.entities.EntityDate;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="TB_RecordPhoto")
public class RecordPhoto extends EntityDate {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne()
	@JoinColumn(name="photo_record")
	private Record record;

	@Column(name="url")
	private String url;
}

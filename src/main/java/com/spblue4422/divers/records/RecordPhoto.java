package com.spblue4422.divers.records;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.spblue4422.divers.common.entities.Image;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="TB_RecordPhoto")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class RecordPhoto extends Image {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recordPhotoId")
	private Long recordPhotoId;

	@ManyToOne()
	@JoinColumn(name="photo_record")
	private Record record;

	@Column(name = "photoOrder")
	private Integer photoOrder;
}

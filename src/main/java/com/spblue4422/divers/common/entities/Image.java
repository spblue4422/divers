package com.spblue4422.divers.common.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Image extends EntityDate{
	@Column(name="originalName")
	protected String originalName;

	@Column(name="savedName")
	protected String savedName;

	@Column(name="savedPath")
	protected String savedPath;
}

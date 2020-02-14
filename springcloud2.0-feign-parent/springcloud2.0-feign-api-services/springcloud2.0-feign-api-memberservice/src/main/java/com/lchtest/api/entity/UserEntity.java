package com.lchtest.api.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author pc
 *
 */
@Data
@Setter
@Getter
@ToString
public class UserEntity {

	private String name;
	private int age;
}

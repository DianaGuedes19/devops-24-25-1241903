/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.payroll;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;


/**
 * @author Greg Turnquist
 */
// tag::code[]
@Entity // <1>
public class Employee {

	private @Id
	@GeneratedValue Long id; // <2>
	private String firstName;
	private String lastName;
	private String description;
	private Integer jobYears;
	private String email;

	public  Employee() {
	}

	public Employee(String firstName, String lastName, String description, String email, Integer jobYears) {
		if(!isParametersInvalid(firstName)){
			this.firstName = firstName;}
		if(!isParametersInvalid(lastName)){
			this.lastName = lastName;}
		if(!isParametersInvalid(description)){
			this.description = description; }
		if (!isEmailInvalid(email)){
			this.email = email;
		}
		if(!isjobYearsInvalid(jobYears)){
			this.jobYears = jobYears; }

	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return Objects.equals(id, employee.id) &&
				Objects.equals(firstName, employee.firstName) &&
				Objects.equals(lastName, employee.lastName) &&
				Objects.equals(description, employee.description) &&
				Objects.equals(jobYears, employee.jobYears)&&
				Objects.equals(email, employee.email);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, firstName, lastName, description, jobYears,email);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getJobYears() {
		return jobYears;
	}

	public void setJobYears(Integer jobYears) {
		this.jobYears = jobYears;
	}
	public boolean isjobYearsInvalid(Integer jobYears){
		return jobYears == null || jobYears<0 ;
	}

	public boolean isEmailInvalid(String email){
		return  email == null || email.isBlank() || !email.contains("@") ;
	}

	public boolean isParametersInvalid (String parameters) {
		return parameters == null || parameters.isBlank();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
// end::code[]

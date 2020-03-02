package com.genus.teach.resource;

import org.springframework.http.HttpStatus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.genus.teach.DemoApplicationTest;
import com.genus.teach.enumeration.LanguageEnum;
import com.genus.teach.model.Teacher_;

public class TeacherResourceTest extends DemoApplicationTest {

	@Test
	public void shouldFindById() throws Exception{
		given()
				.pathParam(Teacher_.ID, 1L)
				.get("/teacher/{id}")
				.then()
				.log()
				.body()
				.and()
				.statusCode(HttpStatus.OK.value())
				.body(
						Teacher_.ID, equalTo(1),
						Teacher_.NAME, equalTo("Joe Due"),
						Teacher_.LANGUAGE, equalTo(LanguageEnum.PT_BR.getCode()),
						Teacher_.STATUS, equalTo(Boolean.TRUE),
						Teacher_.PHOTO, equalTo("/img/due.png"),
						Teacher_.DATE, equalTo("01-03-2020")
				);
	}

}

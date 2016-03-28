/*
 * Copyright [yyyy] [name of copyright owner]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.graphaware.superhero.domain;

import java.net.URL;
import java.util.Set;

import com.graphaware.superhero.domain.converters.UrlConverter;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.Convert;

/**
 * @author Luanne Misquitta
 */
@NodeEntity(label = "Movie")
public class Movie {

	@GraphId private Long id;
	private String title;
	private int year;
	private Rating rating;
	@Convert(UrlConverter.class)
	private URL imdbUrl;

	@Relationship(type = "STARS")
	private Set<Role> stars;



}

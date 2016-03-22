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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

/**
 * @author Luanne Misquitta
 */
@NodeEntity(label = "Comic")
public class Comic {
	private Long id;
	private String title;
	private String author;
	private String artist;
	private Binding binding;
	private boolean available;

	@DateLong
	private Date onSaleDate;

	@Relationship(type = "FEATURED_IN", direction = Relationship.INCOMING)
	private Set<Character> characters = new HashSet<>();
}

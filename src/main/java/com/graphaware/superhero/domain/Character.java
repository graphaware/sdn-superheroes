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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author Luanne Misquitta
 */
@NodeEntity(label = "Character")
public class Character {

	@GraphId private Long id;
	private String name;
	private List<String> alias;
	private String realName;

	@Relationship(type = "ALLY_OF", direction = Relationship.UNDIRECTED)
	private Set<Character> allies = new HashSet<>();

	@Relationship(type = "ENEMY_OF", direction = Relationship.UNDIRECTED)
	private Set<Character> enemies = new HashSet<>();

	@Relationship(type = "FEATURED_IN")
	private Set<Game> gamesFeaturedIn = new HashSet<>();

	@Relationship(type = "FEATURED_IN")
	private Set<Comic> comicsFeaturedIn = new HashSet<>();

	@Relationship(type = "STARS", direction = Relationship.INCOMING)
	private Set<Role> roles = new HashSet<>();

	@Relationship(type = "MEMBER_OF")
	private Set<Team> teams = new HashSet<>();

	public Character() {
	}

	public Character(String name) {
		this.name = name;
	}

	public Character(String name, List<String> alias, String realName) {
		this.name = name;
		this.alias = alias;
		this.realName = realName;
	}

	public String getName() {
		return name;
	}

	public List<String> getAlias() {
		return alias;
	}

	public String getRealName() {
		return realName;
	}
}

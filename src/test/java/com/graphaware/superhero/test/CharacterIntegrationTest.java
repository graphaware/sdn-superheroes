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

package com.graphaware.superhero.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.graphaware.superhero.domain.Character;
import com.graphaware.superhero.domain.Hero;
import com.graphaware.superhero.domain.Villain;
import com.graphaware.superhero.repository.CharacterRepository;
import com.graphaware.superhero.repository.HeroRepository;
import com.graphaware.superhero.repository.VillainRepository;
import com.graphaware.superhero.test.context.TestContext;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Luanne Misquitta
 */
@ContextConfiguration(classes = {TestContext.class})
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CharacterIntegrationTest {

	@Autowired CharacterRepository characterRepository;
	@Autowired VillainRepository villainRepository;
	@Autowired HeroRepository heroRepository;
	@Autowired Session session;


	/*
	Search for characters
	Save a character
	Save a team
	Save a movie
	Save a game
	Save a comic
	 */

	@After
	public void tearDown() {
		session.purgeDatabase();
	}

	@Test
	public void shouldBeAbleToSearchForCharacters() {
		Hero superman = new Hero("Superman");
		heroRepository.save(superman);

		Hero batman = new Hero("Batman");
		heroRepository.save(batman);

		Villain joker = new Villain("Joker");
		villainRepository.save(joker);

		Villain blackManta = new Villain("Black Manta");
		villainRepository.save(blackManta);

		session.clear();

		List<Character> results = characterRepository.findByNameLike("*bat*");
		assertNotNull(results);
		assertEquals(1, results.size());
		assertEquals(batman.getName(), results.get(0).getName());

		results = characterRepository.findByNameLike("*man*");
		assertNotNull(results);
		assertEquals(3, results.size());
		Set<String> resultNames = new HashSet<>();
		for (Character character : results) {
			resultNames.add(character.getName());
		}
		assertTrue(resultNames.contains(superman.getName()));
		assertTrue(resultNames.contains(batman.getName()));
		assertTrue(resultNames.contains(blackManta.getName()));

		results = characterRepository.findByNameLike("*cat*");
		assertNotNull(results);
		assertEquals(0, results.size());

		//Search for superheroes
		List<Hero> heroes = heroRepository.findByNameLike("*man*");
		assertNotNull(heroes);
		assertEquals(2, heroes.size());
		resultNames.clear();
		for (Character character : heroes) {
			resultNames.add(character.getName());
		}
		assertTrue(resultNames.contains(superman.getName()));
		assertTrue(resultNames.contains(batman.getName()));

		//Search for villains
		List<Villain> villains = villainRepository.findByNameLike("*man*");
		assertNotNull(results);
		assertEquals(1, villains.size());
		assertEquals(blackManta.getName(),villains.get(0).getName());
	}


}

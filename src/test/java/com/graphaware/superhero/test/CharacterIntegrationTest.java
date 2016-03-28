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
import com.graphaware.superhero.repository.CharacterRepository;
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
		Character superman = new Character("Superman");
		characterRepository.save(superman);

		Character batman = new Character("Batman");
		characterRepository.save(batman);

		Character robin = new Character("Robin");
		characterRepository.save(robin);

		session.clear();

		List<Character> results = characterRepository.findByNameLike("*bat*");
		assertNotNull(results);
		assertEquals(1, results.size());
		assertEquals(batman.getName(), results.get(0).getName());

		results = characterRepository.findByNameLike("*man*");
		assertNotNull(results);
		assertEquals(2, results.size());
		Set<String> resultNames = new HashSet<>();
		resultNames.add(results.get(0).getName());
		resultNames.add(results.get(1).getName());
		assertTrue(resultNames.contains(superman.getName()));
		assertTrue(resultNames.contains(batman.getName()));

		results = characterRepository.findByNameLike("*cat*");
		assertNotNull(results);
		assertEquals(0, results.size());
	}

}

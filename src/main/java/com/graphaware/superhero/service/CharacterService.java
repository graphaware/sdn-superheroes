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

package com.graphaware.superhero.service;

import java.util.List;

import com.graphaware.superhero.domain.Character;
import com.graphaware.superhero.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Luanne Misquitta
 */
@Service
public class CharacterService {

	@Autowired CharacterRepository characterRepository;

	public List<Character> searchByKeyword(String keyword) {
		return characterRepository.findByNameLike("*" + keyword + "*");
	}

	public Character getById(Long id, int depth) {
		return characterRepository.findOne(id, depth);
	}

	public List<Character> findRelatedCharacters(Long id) {
		return characterRepository.findRelatedCharacters(id);
	}
}

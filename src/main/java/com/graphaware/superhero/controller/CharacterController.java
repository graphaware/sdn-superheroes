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

package com.graphaware.superhero.controller;

import java.util.List;

import com.graphaware.superhero.domain.Character;
import com.graphaware.superhero.repository.CharacterRepository;
import com.graphaware.superhero.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Luanne Misquitta
 */
@RestController
@RequestMapping("/api/")
public class CharacterController {

	@Autowired CharacterService characterService; //TODO add a service

	@RequestMapping(value = "characters/{id}", method = RequestMethod.GET)
	public Character getCharacterById(@PathVariable("id") Long id) {
		return characterService.getById(id, 1);
	}

	@RequestMapping(value = "characters/{id}/related", method = RequestMethod.GET)
	public List<Character> getRelatedCharacters(@PathVariable("id") Long id) {
		return characterService.findRelatedCharacters(id);
	}

	@RequestMapping(value = "characters/search", method = RequestMethod.GET)
	public List<Character> searchByKeyword( @RequestParam(required = true) String keyword) {
		return characterService.searchByKeyword(keyword);
	}



}

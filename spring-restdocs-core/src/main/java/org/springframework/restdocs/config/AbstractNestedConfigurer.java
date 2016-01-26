/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.restdocs.config;

/**
 * Base class for {@link NestedConfigurer} implementations.
 *
 * @param <P> The type of the configurer's parent
 * @author Andy Wilkinson
 */
public abstract class AbstractNestedConfigurer<P> extends AbstractConfigurer implements
		NestedConfigurer<P> {

	private final P parent;

	/**
	 * Creates a new {@code AbstractNestedConfigurer} with the given {@code parent}.
	 * @param parent the parent
	 */
	protected AbstractNestedConfigurer(P parent) {
		this.parent = parent;
	}

	@Override
	public final P and() {
		return this.parent;
	}

}

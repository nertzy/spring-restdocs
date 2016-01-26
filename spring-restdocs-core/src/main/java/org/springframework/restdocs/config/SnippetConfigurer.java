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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.restdocs.RestDocumentationContext;
import org.springframework.restdocs.curl.CurlDocumentation;
import org.springframework.restdocs.http.HttpDocumentation;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.restdocs.snippet.WriterResolver;

/**
 * A configurer that can be used to configure the generated documentation snippets.
 * @param <P> The type of the configurer's parent
 * @param <T> The concrete type of the configurer to be returned from chained methods
 * @author Andy Wilkinson
 */
public abstract class SnippetConfigurer<P, T> extends AbstractNestedConfigurer<P> {

	/**
	 * The name of the attribute that is used to hold the default snippets.
	 */
	public static final String ATTRIBUTE_DEFAULT_SNIPPETS = "org.springframework.restdocs.defaultSnippets";

	private List<Snippet> defaultSnippets = Arrays.asList(
			CurlDocumentation.curlRequest(), HttpDocumentation.httpRequest(),
			HttpDocumentation.httpResponse());

	/**
	 * The default encoding for documentation snippets.
	 *
	 * @see #withEncoding(String)
	 */
	public static final String DEFAULT_SNIPPET_ENCODING = "UTF-8";

	private String snippetEncoding = DEFAULT_SNIPPET_ENCODING;

	/**
	 * Creates a new {@code SnippetConfigurer} with the given {@code parent}.
	 *
	 * @param parent the parent
	 */
	protected SnippetConfigurer(P parent) {
		super(parent);
	}

	@Override
	public void apply(Map<String, Object> configuration, RestDocumentationContext context) {
		((WriterResolver) configuration.get(WriterResolver.class.getName()))
				.setEncoding(this.snippetEncoding);
		configuration.put(ATTRIBUTE_DEFAULT_SNIPPETS, this.defaultSnippets);
	}

	/**
	 * Configures any documentation snippets to be written using the given
	 * {@code encoding}. The default is UTF-8.
	 *
	 * @param encoding the encoding
	 * @return {@code this}
	 */
	@SuppressWarnings("unchecked")
	public T withEncoding(String encoding) {
		this.snippetEncoding = encoding;
		return (T) this;
	}

	/**
	 * Configures the documentation snippets that will be produced by default.
	 *
	 * @param defaultSnippets the default snippets
	 * @return {@code this}
	 */
	@SuppressWarnings("unchecked")
	public T withDefaults(Snippet... defaultSnippets) {
		this.defaultSnippets = Arrays.asList(defaultSnippets);
		return (T) this;
	}
}

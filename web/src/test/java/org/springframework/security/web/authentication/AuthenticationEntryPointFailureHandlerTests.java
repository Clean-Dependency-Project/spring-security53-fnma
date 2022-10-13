/*
 * Copyright 2002-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.security.web.authentication;

import org.junit.jupiter.api.Test;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.web.AuthenticationEntryPoint;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link AuthenticationEntryPointFailureHandler}
 */
public class AuthenticationEntryPointFailureHandlerTests {

	@Test
	void onAuthenticationFailureWhenDefaultsThenAuthenticationServiceExceptionSwallowed() throws Exception {
		AuthenticationEntryPoint entryPoint = mock(AuthenticationEntryPoint.class);
		AuthenticationEntryPointFailureHandler handler = new AuthenticationEntryPointFailureHandler(entryPoint);
		handler.onAuthenticationFailure(null, null, new AuthenticationServiceException("fail"));
	}

	@Test
	void handleWhenRethrowingThenAuthenticationServiceExceptionRethrown() {
		AuthenticationEntryPoint entryPoint = mock(AuthenticationEntryPoint.class);
		AuthenticationEntryPointFailureHandler handler = new AuthenticationEntryPointFailureHandler(entryPoint);
		handler.setRethrowAuthenticationServiceException(true);
		assertThatExceptionOfType(AuthenticationServiceException.class).isThrownBy(
				() -> handler.onAuthenticationFailure(null, null, new AuthenticationServiceException("fail")));
	}

}

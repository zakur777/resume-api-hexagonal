package com.resume.api.commons;

public interface UseCase<Input, Output> {
  Output execute(Input input) throws Exception;
}

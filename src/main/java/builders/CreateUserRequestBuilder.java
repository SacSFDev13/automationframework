package builders;

import model.api.request.User;

public class CreateUserRequestBuilder {
  private User user = new User();

  public CreateUserRequestBuilder withName(String name) {
    user.setName(name);
    return this;
  }

  public CreateUserRequestBuilder withJob(String job) {
    user.setJob(job);
    return this;
  }

  public User build() {
    return user;
  }
}

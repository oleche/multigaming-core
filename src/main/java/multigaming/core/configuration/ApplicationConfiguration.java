package multigaming.core.configuration;

import javax.validation.constraints.NotNull;

public interface ApplicationConfiguration {
    @NotNull Integer getMax();
}

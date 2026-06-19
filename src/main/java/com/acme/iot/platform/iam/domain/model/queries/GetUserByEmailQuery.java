package com.acme.iot.platform.iam.domain.model.queries;

import com.acme.iot.platform.iam.domain.model.valueobjects.EmailAddress;

public record GetUserByEmailQuery(EmailAddress emailAddress) {
}

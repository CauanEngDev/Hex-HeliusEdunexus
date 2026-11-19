package com.cauanengdev.heliusedunexus.domain.model;

import java.math.BigDecimal;
import java.time.MonthDay;

public record Exam(int index, BigDecimal grade, MonthDay date) { }
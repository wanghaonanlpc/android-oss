package com.kickstarter.viewmodels;

import com.kickstarter.KSRobolectricTestCase;

import org.junit.Test;

import rx.observers.TestSubscriber;

public class ResetPasswordViewModelTest extends KSRobolectricTestCase {
  @Test
  public void testResetPasswordViewModel_formValidation() {
    final ResetPasswordViewModel vm = new ResetPasswordViewModel(environment());
    final TestSubscriber<Boolean> test = new TestSubscriber<>();

    vm.outputs.isFormValid().subscribe(test);
    test.assertNoValues();

    vm.inputs.email("incorrect@kickstarter");
    test.assertValues(false);

    vm.inputs.email("hello@kickstarter.com");
    test.assertValues(false, true);
  }

  @Test
  public void testResetPasswordViewModel_resetSuccess() {
    final ResetPasswordViewModel vm = new ResetPasswordViewModel(environment());
    final TestSubscriber<Void> test = new TestSubscriber<>();

    vm.outputs.resetSuccess().subscribe(test);
    test.assertNoValues();

    vm.inputs.resetPasswordClick();
    test.assertNoValues();

    vm.inputs.email("hello@kickstarter.com");
    test.assertNoValues();

    vm.inputs.resetPasswordClick();
    test.assertValueCount(1);
  }
}

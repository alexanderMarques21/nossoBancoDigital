package br.com.bootcamp.nossoBancoDigital.conta.controller.validation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.EnglishSequenceData;
import org.passay.IllegalSequenceRule;
import org.passay.LengthRule;
import org.passay.MessageResolver;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.PropertiesMessageResolver;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

	@Override

	public void initialize(final ValidPassword arg0) {

	}

	@Override

	public boolean isValid(String password, ConstraintValidatorContext context) {
		
		Properties props = new Properties();
		try {
			URL resource = this.getClass().getClassLoader().getResource("messages.properties");
			props.load(new FileInputStream(resource.getPath()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		MessageResolver resolver = new PropertiesMessageResolver(props);
		PasswordValidator validator = new PasswordValidator(resolver,Arrays.asList(

				// length between 8 and 16 characters

				new LengthRule(8, 16),

				new CharacterRule(EnglishCharacterData.UpperCase, 1),

				// at least one lower-case character

				new CharacterRule(EnglishCharacterData.LowerCase, 1),

				new CharacterRule(EnglishCharacterData.Digit, 1),

				// at least one symbol (special character)

				new CharacterRule(EnglishCharacterData.Special, 1),

				// no whitespace

				new WhitespaceRule(),

				// rejects passwords that contain a sequence of >= 5 characters alphabetical
				// (e.g. abcdef)

				new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),

				// rejects passwords that contain a sequence of >= 5 characters numerical (e.g.
				// 12345)

				new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false)

		));

		RuleResult result = validator.validate(new PasswordData(password));

		if (result.isValid()) {

			return true;

		}

		List<String> messages = validator.getMessages(result);

		String messageTemplate = String.join(",", messages);

		context.buildConstraintViolationWithTemplate(messageTemplate)

				.addConstraintViolation()

				.disableDefaultConstraintViolation();

		return false;

	}

}
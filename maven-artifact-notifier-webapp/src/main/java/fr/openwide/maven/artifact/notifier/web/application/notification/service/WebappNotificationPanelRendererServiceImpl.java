package fr.openwide.maven.artifact.notifier.web.application.notification.service;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;

import fr.openwide.core.wicket.more.model.GenericEntityModel;
import fr.openwide.core.wicket.more.notification.model.IWicketNotificationDescriptor;
import fr.openwide.core.wicket.more.notification.service.AbstractNotificationContentDescriptorFactory;
import fr.openwide.maven.artifact.notifier.core.business.artifact.model.ArtifactVersionNotification;
import fr.openwide.maven.artifact.notifier.core.business.notification.service.IArtifactNotifierNotificationContentDescriptorFactory;
import fr.openwide.maven.artifact.notifier.core.business.user.model.EmailAddress;
import fr.openwide.maven.artifact.notifier.core.business.user.model.User;
import fr.openwide.maven.artifact.notifier.core.config.application.MavenArtifactNotifierConfigurer;
import fr.openwide.maven.artifact.notifier.web.application.MavenArtifactNotifierApplication;
import fr.openwide.maven.artifact.notifier.web.application.notification.component.ConfirmEmailHtmlNotificationPanel;
import fr.openwide.maven.artifact.notifier.web.application.notification.component.ConfirmRegistrationHtmlNotificationPanel;
import fr.openwide.maven.artifact.notifier.web.application.notification.component.DeleteEmailHtmlNotificationPanel;
import fr.openwide.maven.artifact.notifier.web.application.notification.component.NewVersionsHtmlNotificationPanel;
import fr.openwide.maven.artifact.notifier.web.application.notification.component.ResetPasswordHtmlNotificationPanel;

@Service("webappNotificationPanelRendererService")
public class WebappNotificationPanelRendererServiceImpl extends AbstractNotificationContentDescriptorFactory
		implements IArtifactNotifierNotificationContentDescriptorFactory<IWicketNotificationDescriptor> {
	
	@Autowired
	private MavenArtifactNotifierConfigurer configurer;
	
	@Override
	public IWicketNotificationDescriptor renderConfirmRegistrationNotificationPanel(final User user) {
		final IModel<User> userModel = GenericEntityModel.of(user);
		return new AbstractSimpleWicketNotificationDescriptor("notification.panel.confirmRegistration") {
			@Override
			public IModel<?> getSubjectParameter() {
				return userModel;
			}
			@Override
			public Iterable<?> getSubjectPositionalParameters() {
				return ImmutableList.of(user.getDisplayName());
			}
			@Override
			public Component createComponent(String wicketId) {
				return new ConfirmRegistrationHtmlNotificationPanel(wicketId, userModel);
			}
		};
	}
	
	@Override
	public IWicketNotificationDescriptor renderResetPasswordNotificationPanel(final User user) {
		final IModel<User> userModel = GenericEntityModel.of(user);
		return new AbstractSimpleWicketNotificationDescriptor("notification.panel.resetPassword") {
			@Override
			public IModel<?> getSubjectParameter() {
				return userModel;
			}
			@Override
			public Iterable<?> getSubjectPositionalParameters() {
				return ImmutableList.of(user.getDisplayName());
			}
			@Override
			public Component createComponent(String wicketId) {
				return new ResetPasswordHtmlNotificationPanel(wicketId, userModel);
			}
		};
	}
	
	@Override
	public IWicketNotificationDescriptor renderNewVersionNotificationPanel(final List<ArtifactVersionNotification> notifications, final User user) {
		final IModel<User> userModel = GenericEntityModel.of(user);
		return new AbstractSimpleWicketNotificationDescriptor("notification.panel.newVersion") {
			@Override
			public IModel<?> getSubjectParameter() {
				return userModel;
			}
			@Override
			public Iterable<?> getSubjectPositionalParameters() {
				return ImmutableList.of(user.getDisplayName());
			}
			@Override
			public Component createComponent(String wicketId) {
				IModel<List<ArtifactVersionNotification>> notificationsModel = new ListModel<ArtifactVersionNotification>(notifications);
				return new NewVersionsHtmlNotificationPanel(wicketId, notificationsModel);
			}
		};
	}
	
	@Override
	public IWicketNotificationDescriptor renderNewVersionNotificationPanel(final List<ArtifactVersionNotification> notifications, final EmailAddress emailAddress) {
		final IModel<EmailAddress> emailAddressModel = GenericEntityModel.of(emailAddress);
		return new AbstractSimpleWicketNotificationDescriptor("notification.panel.newVersion") {
			@Override
			public IModel<?> getSubjectParameter() {
				return emailAddressModel;
			}
			@Override
			public Iterable<?> getSubjectPositionalParameters() {
				return ImmutableList.of(emailAddress.getDisplayName());
			}
			@Override
			public Component createComponent(String wicketId) {
				IModel<List<ArtifactVersionNotification>> notificationsModel = new ListModel<ArtifactVersionNotification>(notifications);
				return new NewVersionsHtmlNotificationPanel(wicketId, notificationsModel, emailAddressModel);
			}
		};
	}
	
	@Override
	public IWicketNotificationDescriptor renderConfirmEmailNotificationPanel(final EmailAddress emailAddress) {
		final IModel<EmailAddress> emailAddressModel = GenericEntityModel.of(emailAddress);
		return new AbstractSimpleWicketNotificationDescriptor("notification.panel.confirmEmail") {
			@Override
			public IModel<?> getSubjectParameter() {
				return emailAddressModel;
			}
			@Override
			public Iterable<?> getSubjectPositionalParameters() {
				return ImmutableList.of(emailAddress.getDisplayName());
			}
			@Override
			public Component createComponent(String wicketId) {
				return new ConfirmEmailHtmlNotificationPanel(wicketId, emailAddressModel);
			}
		};
	}
	
	@Override
	public IWicketNotificationDescriptor renderDeleteEmailNotificationPanel(final EmailAddress emailAddress) {
		final IModel<EmailAddress> emailAddressModel = GenericEntityModel.of(emailAddress);
		return new AbstractSimpleWicketNotificationDescriptor("notification.panel.deleteEmail") {
			@Override
			public IModel<?> getSubjectParameter() {
				return emailAddressModel;
			}
			@Override
			public Iterable<?> getSubjectPositionalParameters() {
				return ImmutableList.of(emailAddress.getDisplayName());
			}
			@Override
			public Component createComponent(String wicketId) {
				return new DeleteEmailHtmlNotificationPanel(wicketId, emailAddressModel);
			}
		};
	}
	
	@Override
	protected String getApplicationName() {
		return MavenArtifactNotifierApplication.NAME;
	}
}

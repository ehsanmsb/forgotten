# CI/CD Pipeline for Project snapp-app

This repository contains the GitLab CI/CD configuration for automating the build, versioning, and create helm processes for Project snapp-app.

## Pipeline Overview

The CI/CD pipeline is designed to perform the following stages:

1. **Versioning**: The Semantic Versioning Runner is used to determine the new version based on semantic versioning rules. If a new version is determined, it's used for the rest of the pipeline. This stage runs only when specific conditions are met.

2. **Artifact**: In this stage, the Maven project is built and the generated artifact is deployed to a nexus repository. The deployment happens only if a new version was successfully determined.

3. **Build**: This stage is responsible for building a Docker image using the built artifact and pushing it to a container registry. This stage also checks for a new version before proceeding.

4. **Helm**: Manages the Helm chart deployment for the application.Clones the Helm chart repository, updates the version, and pushes the changes.Requires the versioning stage to have completed successfully.

## Pipeline Configuration

The pipeline is controlled through `.gitlab-ci.yml` file present in the root of the repository. The file is divided into stages, each with its own set of tasks and conditions.

### Versioning Stage

- Uses Semantic Versioning Runner to determine a new version.
- Updates the `pom.xml` with the new version.
- Commits the changes and pushes them to the repository.
- Sets the `VERSION` environment variable for later stages.

### Artifact Stage

- Builds the Maven project and generates the artifact.
- Deploys the artifact to a repository.
- This stage runs only if a new version has been determined in the Versioning stage.

### Build Stage

- Uses Docker to build an image with the artifact.
- Pushes the image to a container registry.
- This stage also checks for a new version before proceeding.

### Helm stage

- Manages the Helm chart deployment for the application.
- Clones the Helm chart repository, updates the version, and pushes the changes.
- Requires the versioning stage to have completed successfully.

## Environment Variables

- `MAVEN_CLI_OPTS`: Maven command line options for running builds.
- `DEPLOY_NAMESPACE`: Namespace for deploying the Docker image.
- `VERSION_FLAG`: Flag indicating whether a new version was determined.

## Tags

- `docker`: This tag is used for jobs that involve Docker operations.
- `shell`: This tag is used for jobs that involve shell scripting.

## Rules and Conditions

- Jobs are triggered based on pipeline source (`$CI_PIPELINE_SOURCE`) and commit branch (`$CI_COMMIT_BRANCH`) conditions.
- Jobs related to the Versioning stage run when pushing to the `build-ci-cd` branch.
- Jobs related to the Artifact and Build stages run on successful completion of the previous stages or on a web-triggered pipeline.

## Reports and Artifacts

- The pipeline generates a `version.env` file that contains the new version.
- Artifacts and Docker images are stored as reports.

## Getting Started

1. Configure the environment variables in GitLab CI/CD settings.
2. Adjust the pipeline conditions and configurations as per your project's needs.
3. Trigger the pipeline manually or through webhook events.

For more information about the GitLab CI/CD configuration, consult the [GitLab CI/CD documentation](https://docs.gitlab.com/ee/ci/).

######


package ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
// body is "Text" in the Figma Design
// label small is xsmall in Figma Design
private val defaultTypography = Typography()
val Typography = Typography(
    displaySmall = defaultTypography.displaySmall.copy(
        fontSize = 24.sp,
        lineHeight = 36.sp,
    ),

    displayMedium = defaultTypography.displayMedium.copy(
        fontSize = 32.sp,
        lineHeight = 48.sp,
    ),
    bodySmall = defaultTypography.bodySmall.copy(
        fontSize = 14.sp,
        lineHeight = 21.sp,
    ),
    bodyMedium = defaultTypography.bodyMedium.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    labelSmall = defaultTypography.labelSmall.copy(
        fontSize = 13.sp,
        lineHeight = 19.sp,
    ),
)